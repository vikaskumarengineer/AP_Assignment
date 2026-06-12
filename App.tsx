import React, {useState} from 'react';
import {View, Text, TouchableOpacity, StyleSheet, StatusBar, SafeAreaView} from 'react-native';

export default function App(): React.JSX.Element {
  const [count, setCount] = useState<number>(0);
  const [isDarkMode, setIsDarkMode] = useState<boolean>(false);

  const handleIncrement = () => setCount(prev => prev + 1);
  const handleDecrement = () => {if (count > 0) setCount(prev => prev - 1);};
  const handleReset = () => setCount(0);
  const toggleTheme = () => setIsDarkMode(prev => !prev);

  const bg = isDarkMode ? '#1A1A1A' : '#F5F5F5';
  const textColor = isDarkMode ? '#F5F5F5' : '#1A1A1A';
  const accent = isDarkMode ? '#60A5FA' : '#2563EB';
  const btnBg = isDarkMode ? '#2D2D2D' : '#FFFFFF';

  return (
    <SafeAreaView style={[styles.safe, {backgroundColor: bg}]}>
      <StatusBar barStyle={isDarkMode ? 'light-content' : 'dark-content'} backgroundColor={bg} />
      <View style={[styles.container, {backgroundColor: bg}]}>
        <Text style={[styles.title, {color: textColor}]}>Digital Counter</Text>
        <View style={[styles.circle, {borderColor: accent}]}>
          <Text style={[styles.count, {color: accent}]}>{count}</Text>
        </View>
        <View style={styles.row}>
          <TouchableOpacity style={[styles.btn, styles.half, {backgroundColor: btnBg}]} onPress={handleDecrement}>
            <Text style={[styles.btnText, {color: textColor}]}>- Decrement</Text>
          </TouchableOpacity>
          <TouchableOpacity style={[styles.btn, styles.half, {backgroundColor: btnBg}]} onPress={handleIncrement}>
            <Text style={[styles.btnText, {color: textColor}]}>+ Increment</Text>
          </TouchableOpacity>
        </View>
        <TouchableOpacity style={[styles.btn, styles.full, {backgroundColor: '#DC2626'}]} onPress={handleReset}>
          <Text style={[styles.btnText, {color: '#fff'}]}>Reset</Text>
        </TouchableOpacity>
        <TouchableOpacity style={[styles.btn, styles.full, {backgroundColor: '#7C3AED'}]} onPress={toggleTheme}>
          <Text style={[styles.btnText, {color: '#fff'}]}>{isDarkMode ? 'Switch to Light Mode' : 'Switch to Dark Mode'}</Text>
        </TouchableOpacity>
        <Text style={[styles.label, {color: textColor}]}>{isDarkMode ? 'Dark Mode Active' : 'Light Mode Active'}</Text>
      </View>
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  safe: {flex: 1},
  container: {flex: 1, justifyContent: 'center', alignItems: 'center', paddingHorizontal: 24},
  title: {fontSize: 28, fontWeight: '700', marginBottom: 32},
  circle: {width: 160, height: 160, borderRadius: 80, borderWidth: 4, justifyContent: 'center', alignItems: 'center', marginBottom: 40},
  count: {fontSize: 64, fontWeight: '800'},
  row: {flexDirection: 'row', gap: 12, marginBottom: 12, width: '100%'},
  btn: {borderRadius: 12, paddingVertical: 14, alignItems: 'center', justifyContent: 'center', elevation: 2},
  half: {flex: 1},
  full: {width: '100%', marginBottom: 12},
  btnText: {fontSize: 16, fontWeight: '600'},
  label: {marginTop: 8, fontSize: 13},
});
