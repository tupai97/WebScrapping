package com.Nike.NikeScrape;


import java.io.IOException;

public class Tester {

	public static void main(String[] args) throws InterruptedException, IOException {
		NikeScrape sa = new NikeScrape();
		sa.setup();
		sa.info();
		sa.teardown();
		sa.loopAndWriteFile();	
	}
}
