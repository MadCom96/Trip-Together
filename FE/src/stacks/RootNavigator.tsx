import {createNativeStackNavigator} from '@react-navigation/native-stack';
import React from 'react';
import {RootStackParams} from '../interfaces/router/RootStackParams';
import Login from '../pages/Login';
import PinAuth from '../pages/PinAuth';
import SocialLogin from '../pages/SocialLogin';
import {RootState} from '../store';
import {useAppSelector} from '../store/hooks';
import TabNavigator from './TabNavigator';

const Stack = createNativeStackNavigator<RootStackParams>();

const RootNavigator = () => {
  const token = useAppSelector((state: RootState) => state.user.token);

  return (
    <Stack.Navigator screenOptions={{headerShown: false}}>
      {token ? (
        <>
          <Stack.Screen name="Main" component={TabNavigator} />
          <Stack.Screen
            name="PinAuth"
            component={PinAuth}
            options={{title: '핀 인증', headerShown: true}}
          />
        </>
      ) : (
        <>
          <Stack.Screen name="Login" component={Login} />
          <Stack.Screen name="SocialLogin" component={SocialLogin} />
        </>
      )}
    </Stack.Navigator>
  );
};

export default RootNavigator;
