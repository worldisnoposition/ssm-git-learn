package com.learn.hsf;

public class ItemServiceImpl implements ItemService {
    @Override
    public Item getItemById( long id ) {
        Item car = new Item();
        car.setItemId( 1l );
        car.setItemName( "Mercedes Benz" );
        return car;
    }
    @Override
    public Item getItemByName( String name ) {
        Item car = new Item();
        car.setItemId( 1l );
        car.setItemName( "Mercedes Benz" );
        return car;
    }
}
