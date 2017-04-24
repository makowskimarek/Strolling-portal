package com.walker.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Rafal on 21.03.2017.
 */
@Component
public class PersonBase {

    public List<PersonStructure> personBase;

    public PersonBase()
    {
        personBase = new ArrayList<PersonStructure>();
    }

    public List<String> takeAll()
    {
        List<String> result = new ArrayList<String>();
        for(PersonStructure personStructure : personBase)
        {
           result.add(personStructure.to_String());
        }

        return result;
    }

    public List<String> takeAllWithDate(Date data)
    {
        List<String> result = new ArrayList<String>();
        for(PersonStructure personStructure : personBase)
        {
            if(personStructure.getData() == data)
                result.add(personStructure.toString());
        }
        return result;
    }

}
