package com.pankaj.codeshop;

import java.io.Serializable;

public class SaveListItem implements Serializable
{
 String stitle,sdate,shtml;
    byte[] simg;

    @Override
    public String toString()
    {
        return stitle;
    }

}
