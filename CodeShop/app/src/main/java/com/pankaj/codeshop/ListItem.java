package com.pankaj.codeshop;

import java.io.Serializable;

public class ListItem implements Serializable
{
 String ltitle,llink,limg,ldt;

    @Override
    public String toString()
    {
        return ltitle;
    }

}
