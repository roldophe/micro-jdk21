package dev.radom.cards.service.impl;

import dev.radom.cards.constant.CardConstant;
import dev.radom.cards.dto.CardDto;
import dev.radom.cards.entity.Card;
import dev.radom.cards.exception.CardAlreadyExistsException;
import dev.radom.cards.exception.ResourceNotFoundException;
import dev.radom.cards.mapper.CardMapper;
import dev.radom.cards.repository.CardRepository;
import dev.radom.cards.service.CardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

/**
 * Implementation of the CardService interface providing
 * the business logic for managing cards.
 */
@AllArgsConstructor
@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    /**
     * Creates a new card for the given mobile number.
     *
     * @param mobileNumber - Mobile Number of the Customer
     * @throws CardAlreadyExistsException if a card already exists for the given mobile number
     */
    @Override
    public void createCard(String mobileNumber) {
        Optional<Card> optionalCard = cardRepository.findByMobileNumber(mobileNumber);
        if (optionalCard.isPresent()) {
            throw new CardAlreadyExistsException("Card already registered with given mobileNumber " + mobileNumber);
        }
        cardRepository.save(createNewCard(mobileNumber));
    }

    /**
     * Creates a new Card object with default values for a given mobile number.
     *
     * @param mobileNumber - Mobile Number of the Customer
     * @return the new card details
     */
    private Card createNewCard(String mobileNumber) {
        Card newCard = new Card();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardConstant.CREDIT_CARD);
        newCard.setTotalLimit(CardConstant.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardConstant.NEW_CARD_LIMIT);
        return newCard;
    }

    /**
     * Fetches card details based on a given mobile number.
     *
     * @param mobileNumber - Input mobile Number
     * @return Card Details based on a given mobileNumber
     * @throws ResourceNotFoundException if no card is found for the given mobile number
     */
    @Override
    public CardDto fetchCard(String mobileNumber) {
        Card card = cardRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
        );
        return CardMapper.mapToCardDto(card, new CardDto());
    }

    /**
     * Updates card details based on the provided CardDto.
     *
     * @param cardDto - cardDto Object
     * @return boolean indicating if the update of card details is successful or not
     * @throws ResourceNotFoundException if no card is found for the given card number
     */
    @Override
    public boolean updateCard(CardDto cardDto) {
        Card card = cardRepository.findByCardNumber(cardDto.getCardNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Card", "cardNumber", cardDto.getCardNumber())
        );
        CardMapper.mapToCard(cardDto, card);
        cardRepository.save(card);
        return true;
    }

    /**
     * Deletes card details based on a given mobile number.
     *
     * @param mobileNumber - Input Mobile Number
     * @return boolean indicating if the delete of card details is successful or not
     * @throws ResourceNotFoundException if no card is found for the given mobile number
     */
    @Override
    public boolean deleteCard(String mobileNumber) {
        Card card = cardRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
        );
        cardRepository.deleteById(card.getCardId());
        return true;
    }
}