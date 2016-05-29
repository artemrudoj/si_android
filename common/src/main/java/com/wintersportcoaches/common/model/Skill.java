package com.wintersportcoaches.common.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by artem on 28.05.16.
 */
public class Skill {
    String kindOfSport;
    HashMap<String, Integer> skillsWithPrices;

    public Skill(String kindOfSport, HashMap<String, Integer> hashMap) {
        this.kindOfSport = kindOfSport;
        skillsWithPrices = hashMap;
    }

    public String getKindOfSport() {
        return kindOfSport;
    }

    public HashMap<String, Integer> getSkillsWithPrices() {
        return skillsWithPrices;
    }
}
