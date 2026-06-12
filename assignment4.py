
products [ 
    {"name" : "book", "stock": 5}, 
    {"name": "notebook", "stock": 8}, 
    {"name": "pen", "stock": 15}, 
    {"name": "laptop", "stock": 3}, 
    {"name": "charger", "stock": 12}, 
    {"name": "bag", "stock": 2}, 
    {"name": "mobile phone", "stock": 7}, 
    {"name": "wifi", "stock": 1}, 
    {"name": "power", "stock": 9} 
]
print("Products with stock less than 10:") 
    for product in products: 
    if product["stock"] < 10: 
