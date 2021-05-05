package com.prospring.ch8.service;

import java.util.List;

import com.prospring.ch8.view.SingerSummary;

public interface SingerSummaryService {
	List<SingerSummary> findAll();
}
