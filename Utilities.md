# Keyboard #

A primative on-screen keyboard.

## Use ##
Allows the player to input strings for save names, character names etc.

## Implementation ##
An implementing canvas needs to create an instance of the keyboard, then call setFocus(true) to give the keyboard input focus when required.

While the keyboard has focus, the canvas must pass all keypresses through to its gotKeypress() method. This method returns a string if the key pressed was 'KEY\_FIRE'. either a character, 'SPACE' or 'DONE'.

## ToDo ##
Add 'backspace' functionality.