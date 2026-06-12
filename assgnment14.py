import sys
import gc


class Node:
    def __init__(self, name):
        self.name = name
        self.link = None


nodes = None

while True:
    print("\n" + "=" * 60)
    print("ASSIGNMENT 14: GARBAGE COLLECTION DEMO")
    print("=" * 60)
    print("1. Create reference cycle")
    print("2. Show reference counts")
    print("3. Delete references (make objects dead)")
    print("4. Inspect dead objects in memory")
    print("5. Cleanup - Call gc.collect()")
    print("6. Exit")
    print("=" * 60)

    choice = input("Enter your choice (1-6): ")

    if choice == "1":
        print("\n--- CREATE REFERENCE CYCLE ---")
        name1 = input("Enter name for first node: ")
        name2 = input("Enter name for second node: ")

        node1 = Node(name1)
        node2 = Node(name2)

        node1.link = node2
        node2.link = node1

        nodes = (node1, node2)

        print(f"\n✓ Cycle created!")
        print(f"  {node1.name} --> {node2.name}")
        print(f"  {node2.name} --> {node1.name}")

    elif choice == "2":
        print("\n--- REFERENCE COUNTS ---")
        if nodes:
            n1, n2 = nodes
            print(f"  Reference count for '{n1.name}': {sys.getrefcount(n1)}")
            print(f"  Reference count for '{n2.name}': {sys.getrefcount(n2)}")
            print("\n  → Both have multiple references due to cycle!")
        else:
            print("  ❌ No cycle exists! First choose option 1.")

    elif choice == "3":
        print("\n--- DELETE REFERENCES ---")
        if nodes:
            confirm = input("  Delete direct references? (yes/no): ")
            if confirm.lower() == "yes":
                nodes = None
                print("  ✓ Direct references deleted!")
                print("  → Objects are now 'DEAD' but still in memory!")
            else:
                print("  ✗ Deletion cancelled.")
        else:
            print("  ❌ Nothing to delete! First create a cycle.")

    elif choice == "4":
        print("\n--- INSPECT DEAD OBJECTS ---")
        gc.collect()
        all_objects = gc.get_objects()
        dead_objects = [obj for obj in all_objects if isinstance(obj, Node)]

        if dead_objects:
            print(f"  ⚠️ Found {len(dead_objects)} dead object(s) still in memory!")
            for obj in dead_objects:
                print(f"     → {obj.name} still exists and points to {obj.link.name}")
            print("\n  → These objects are 'DEAD' but garbage collector didn't clean them!")
        else:
            print("  ✓ No dead objects found in memory!")

    elif choice == "5":
        print("\n--- CLEANUP - CALLING gc.collect() ---")
        confirm = input("  Force garbage collection? (yes/no): ")

        if confirm.lower() == "yes":
            print("\n  Running garbage collector...")
            collected = gc.collect()
            print(f"\n  ✓ Number of unreachable objects collected: {collected}")

            all_objects = gc.get_objects()
            dead_objects = [obj for obj in all_objects if isinstance(obj, Node)]
            print(f"  Objects remaining in memory: {len(dead_objects)}")
        else:
            print("  ✗ Cleanup cancelled.")

    elif choice == "6":
        print("\n  Exiting program...")
        print("  All memory will be freed on exit.")
        break

    else:
        print("  ❌ Invalid choice! Please enter 1-6.")

print("\n" + "=" * 60)
print("PROGRAM ENDED")
print("=" * 60)
