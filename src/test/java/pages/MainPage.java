package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {
    private SelenideElement searchInput = $("#searchInput");
    private ElementsCollection productCards = $$(".product-card__wrapper");
    private ElementsCollection navbarItems = $$(".navbar-pc__item");
    private SelenideElement basketEmptyButton = $(".basket-empty__btn");
    private ElementsCollection serviceMenuList = $$(".service-menu__list li");
    private SelenideElement searchPhotoButton = $(".search-catalog__btn-wrap");
    private SelenideElement searchPhotoDndPlace = $("#uploadImageForSearchByImagePopUpContainer");
    private SelenideElement addGoodButton = $(".product-card__add-basket");
    private SelenideElement deleteGoodButton = $(".btn__del");
    private SelenideElement goodEntity = $(".basket-list__accordion");
    private SelenideElement notFoundSearchTitile = $(".not-found-search__title");
    private SelenideElement validSearchTitile = $(".searching-results__title");
    private SelenideElement burgerMenuButton = $(".nav-element__burger");
    private ElementsCollection burgerMenuFirstListItem = $$(".menu-burger__main-list-link");
    private SelenideElement burgerMenuSecondListItem = $(".menu-burger__link");
    private SelenideElement priceCard = $(".product-card__middle-wrap");

    public MainPage openPage() {
        open("");
        return this;
    }

    public MainPage seatchInputClick(String searchQuery) {
        searchInput.setValue(searchQuery).shouldBe(visible).pressEnter();
        return this;
    }

    public MainPage clickOnNavbarButton(String chapter) {
        navbarItems.findBy(text(chapter)).click();
        return this;
    }

    public MainPage clickOnPhotoSearch() {
        searchPhotoButton.hover().click();
        return this;
    }

    public MainPage addToBasketGood() {
        addGoodButton.click();
        return this;
    }

    public MainPage deleteFromBasketGood() {
        deleteGoodButton.click();
        return this;
    }
    public MainPage clickburgerMenuButton() {
        burgerMenuButton.click();
        return this;
    }
    public MainPage hoverFirtListTitle(String value) {
        burgerMenuFirstListItem.findBy(text(value)).hover();
        return this;
    }
    public MainPage clickSecondListTitle(String value) {
        burgerMenuSecondListItem.click();
        return this;
    }



    //Проверки
    public MainPage searchResultShouldBeGreaterThan(Integer count) {
        productCards.shouldBe(sizeGreaterThan(count));
        return this;
    }

    public MainPage emptyBusketShouldHaveButton(String buttonText) {
        basketEmptyButton.shouldHave(text(buttonText));
        return this;
    }

    public MainPage titlesOnCkickBattonAddressResult(List<String> expectedLinks) {
        serviceMenuList.filter(visible).shouldHave(texts(expectedLinks));
        return this;
    }

    public MainPage popUpclickOnPhotoSearch() {
        searchPhotoDndPlace.shouldBe(visible);
        return this;
    }
    public MainPage goodEntityIsExist() {
        goodEntity.shouldBe(exist);
        return this;
    }
    public MainPage goodEntityIsNotExist() {
        goodEntity.shouldBe(not(exist));
        return this;
    }
    public MainPage notFoundSearchTitleExist(String text) {
        notFoundSearchTitile.shouldBe(exist);
        notFoundSearchTitile.shouldHave(text("Ничего не нашлось по запросу «"+text+"»"));
        return this;
    }

    public MainPage validSearchTitleExist(String text) {
        validSearchTitile.shouldBe(exist);
        validSearchTitile.shouldHave(text(text));
        return this;
    }

    public MainPage priceShouldBeVisible() {
        priceCard.shouldBe(visible, Duration.ofSeconds(10));
        return this;
    }


}

