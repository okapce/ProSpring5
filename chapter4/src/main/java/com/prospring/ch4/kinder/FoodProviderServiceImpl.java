package com.prospring.ch4.kinder;

import java.util.List;
import java.util.ArrayList;


import com.prospring.ch4.Food;
import com.prospring.ch4.FoodProviderService;

public class FoodProviderServiceImpl  implements FoodProviderService {
	@Override
	 public List<Food> provideLunchSet() {
	 List<Food> lunchSet = new ArrayList<>();
	 lunchSet.add(new Food("Milk"));
	 lunchSet.add(new Food("Biscuits"));
	 return lunchSet;
	}
}
