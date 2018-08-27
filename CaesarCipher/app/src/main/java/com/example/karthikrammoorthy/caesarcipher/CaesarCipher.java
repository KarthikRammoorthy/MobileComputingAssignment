package com.example.karthikrammoorthy.caesarcipher;

/**
 * Created by karthikrammoorthy on 22/01/18.
 */

public class CaesarCipher {

    /*
        * Declare Variables for Cipher and constructor
        * Sets the user input values and gets the crypted plain text
         */

    private String input;
    private String cryptedInput;
    private Integer shiftValue;

public void setInput(String input){
    this.input = input;
}
public void setShiftVlaue(Integer shiftValue){
    this.shiftValue = shiftValue;
}
public String getCryptedInput(){
    return this.cryptedInput;
}
public CaesarCipher (){}

/*
* Add cipher class to encrypt/decrypt input
*/

public void caesar(){

    String cryptedInput = "";
    char letter;
    int ascii;

    for (int i = 0; i < this.input.length() ; i++){
        letter = this.input.charAt(i);

        if (letter != ' ') {
            ascii = (int) letter;
            ascii = ascii + (this.shiftValue % 26);

            ascii = ascii > 'Z' ? ascii - 26 : ascii;

            cryptedInput = cryptedInput + (char) ascii;
        }
        else {

            cryptedInput = cryptedInput + ' ';

        }
    }


    this.cryptedInput = cryptedInput;

}

}
