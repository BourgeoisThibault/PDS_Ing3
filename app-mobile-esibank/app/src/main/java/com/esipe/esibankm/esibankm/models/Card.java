package com.esipe.esibankm.esibankm.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by usman on 14/04/2018.
 */

public class Card implements Serializable {
    @SerializedName("card_id")
    private Long card_id;

    public Long getCard_id() {
        return card_id;
    }

    @Override
    public String toString() {
        return "Card{" +
                "card_id=" + card_id +
                ", cardNum='" + cardNum + '\'' +
                ", cardPass='" + cardPass + '\'' +
                '}';
    }

    public void setCard_id(Long card_id) {
        this.card_id = card_id;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getCardPass() {
        return cardPass;
    }

    public void setCardPass(String cardPass) {
        this.cardPass = cardPass;
    }

    @SerializedName("card_num")

    private String cardNum;
    @SerializedName("card_pass")
    private String cardPass;

}
