package fr.unice.polytech.si4.conception.l;

import java.util.Collections;
import java.util.List;
import com.google.common.collect.ImmutableList;

/**
 * This class is the Customer side of the CookieFactory, indeed the customer shouldn't
 * get access to all the value of the CookieFactory, so this class serves as a proxy
 */
public  class FactoryCustomerSide implements ICookieFactory{

    CookieFactory cookieFactory;

    public FactoryCustomerSide() {
        this.cookieFactory = CookieFactory.getInstance();
    }

    @Override
    public List<Cookie> getCookies() {
        return Collections.unmodifiableList(cookieFactory.getCookies());
    }

    @Override
    public List<Store> getStores() {
        return Collections.unmodifiableList(cookieFactory.getStores());
    }

    @Override
    public List<Ingredient> getIngredients() {
        return Collections.unmodifiableList(cookieFactory.getIngredients());
    }

}
