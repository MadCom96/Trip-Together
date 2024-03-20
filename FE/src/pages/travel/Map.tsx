import React from 'react';
import {SafeAreaView, Text} from 'react-native';
import GoogleMap from '../../components/travel/GoogleMap';
import SearchPlace from '../../components/travel/SearchPlace';
import SlidingUpPanel from 'rn-sliding-up-panel';
import {
  Container,
  SliderContainer,
  DragBar,
  SliderFullContent,
  ButtonImage,
  PlanImage,
  PlanIcon,
} from './MapStyle';

const Map = () => {
  return (
    <SafeAreaView style={{flex: 1}}>
      <Container>
        <GoogleMap />
        <SearchPlace />
        <PlanIcon>
          <PlanImage source={require('../../assets/images/planning.png')} />
        </PlanIcon>
        {/* <PlaceInfo /> */}
        <SliderContainer>
          <DragBar onPress={() => this._panel.show()}>
            <ButtonImage source={require('../../assets/images/up.png')} />
          </DragBar>
          <SlidingUpPanel ref={c => (this._panel = c)}>
            <SliderFullContent>
              <Text>Here is the content inside panel</Text>
              <DragBar onPress={() => this._panel.hide()}>
                <ButtonImage source={require('../../assets/images/down.png')} />
              </DragBar>
            </SliderFullContent>
          </SlidingUpPanel>
        </SliderContainer>
      </Container>
    </SafeAreaView>
  );
};
export default Map;
