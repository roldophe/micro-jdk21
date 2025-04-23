package dev.radom.cards.service;

import dev.radom.cards.dto.CardDto;

public interface CardService {

    /**
     *
     * @param mobileNumber - Mobile Number of the Customer
     */
    void createCard(String mobileNumber);

    /**
     *
     * @param mobileNumber - Input mobile Number
     *  @return Card Details based on a given mobileNumber
     */
    CardDto fetchCard(String mobileNumber);

    /**
     *
     * @param cardDto - cardDto Object
     * @return boolean indicating if the update of card details is successful or not
     */
    boolean updateCard(CardDto cardDto);

    /**
     *
     * @param mobileNumber - Input Mobile Number
     * @return boolean indicating if the delete of card details is successful or not
     */
    boolean deleteCard(String mobileNumber);
}