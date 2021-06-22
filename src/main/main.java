package main;


import java.util.*;

import connect.Connect;
import view.menuFrame;
import view.productView;
import view.homePage;

public class main{
	
	public main() {
		Connect con = new Connect();
//		menuFrame mF = new menuFrame();
		homePage hp = new homePage();
	}
	
	public static void main(String[] args) {
		new main();
	}
}
