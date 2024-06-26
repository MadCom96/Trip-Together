import React from 'react';
import {defaultStyle} from '../../constants/AppButton';
import {bg_lightgray} from '../../constants/colors';
import {AppButtonProps} from '../../interfaces/props/AppButton';
import {Btn, BtnBox, BtnText} from './AppButtonStyle';

const AppButton = ({
  style = defaultStyle,
  disabled = false,
  text,
  onPress,
}: AppButtonProps) => {
  const bg1 = style.button?.bg1 ? style.button.bg1 : defaultStyle.button.bg1;
  const bg2 = style.button?.bg2 ? style.button.bg2 : defaultStyle.button.bg2;

  return (
    <BtnBox>
      <Btn
        $width={
          style.button?.width ? style.button.width : defaultStyle.button.width
        }
        $padding={
          style.button?.padding
            ? style.button.padding
            : defaultStyle.button.padding
        }
        $borderR={
          style.button?.borderR
            ? style.button.borderR
            : defaultStyle.button.borderR
        }
        $borderW={
          style.button?.borderW
            ? style.button.borderW
            : defaultStyle.button.borderW
        }
        $borderC={
          style.button?.borderC
            ? style.button.borderC
            : defaultStyle.button.borderC
        }
        onPress={onPress}
        style={({pressed}) => ({
          backgroundColor: disabled ? bg_lightgray : pressed ? bg2 : bg1,
        })}
        disabled={disabled}>
        <BtnText
          $color={
            style.font?.color ? style.font.color : defaultStyle.font.color
          }
          $size={style.font?.size ? style.font.size : defaultStyle.font?.size}>
          {text}
        </BtnText>
      </Btn>
    </BtnBox>
  );
};

export default AppButton;
