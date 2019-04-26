package Test;

import static org.junit.Assert.*;
import org.junit.Test;
import Model.Main1;
import Model.RedBlackTree;
import Model.Market;

public class BVC_Test {

	private RedBlackTree<Market> linkRBT;
	
	@Test
	public void setUp1() {

		linkRBT = new RedBlackTree<Market>();
		Market one = new Market("GBPCAD","20/3/2019", "19:48", "1.75483");
		Market two = new Market("GBPCAD", "20/3/2019", "19:47", "1.75483");
		Market three = new Market("GBPCAD", "20/3/2019", "19:46", "1.75483");
		Market four = new Market("GBPCAD", "22/2/2019", "3:36", "1.75752");
		Market five = new Market("GBPCAD", "22/2/2019", "3:35", "1.7576");
		Market six = new Market("GBPCAD", "22/2/2019", "3:34", "1.75768");
				
		linkRBT.add(one);
		linkRBT.add(two);
		linkRBT.add(three);
		linkRBT.add(four);
		linkRBT.add(five);
		linkRBT.add(six);
	}
	
	@Test
	public void setUp2() {
		
		linkRBT = new RedBlackTree<Market>();
		Market one = new Market("GBPCAD","20/3/2019", "19:48", "1.75483");
		Market two = new Market("GBPCAD", "20/3/2019", "19:47", "1.75483");
		Market three = new Market("GBPCAD", "20/3/2019", "19:46", "1.75483");
		Market four = new Market("GBPCAD", "22/2/2019", "3:36", "1.75752");
		Market five = new Market("GBPCAD", "22/2/2019", "3:35", "1.7576");
		Market six = new Market("GBPCAD", "22/2/2019", "3:34", "1.75768");
				
		linkRBT.add(one);
		linkRBT.add(two);
		linkRBT.add(three);
		linkRBT.add(four);
		linkRBT.add(five);
		linkRBT.add(six);
		
		linkRBT.delete(three);
		
	}
	@Test
	public void setUp3() {
		linkRBT = new RedBlackTree<Market>();
		Market one = new Market("GBPCAD","20/3/2019", "19:48", "1.75483");
		Market two = new Market("GBPCAD", "20/3/2019", "19:47", "1.75483");
		Market three = new Market("GBPCAD", "20/3/2019", "19:46", "1.75483");
		Market four = new Market("GBPCAD", "22/2/2019", "3:36", "1.75752");
		Market five = new Market("GBPCAD", "22/2/2019", "3:35", "1.7576");
		Market six = new Market("GBPCAD", "22/2/2019", "3:34", "1.75768");
				
		linkRBT.add(one);
		linkRBT.add(two);
		linkRBT.add(three);
		linkRBT.add(four);
		linkRBT.add(five);
		linkRBT.add(six);
	}
	@Test
	public void setUp4() {
		linkRBT = new RedBlackTree<Market>();
		Market one = new Market("GBPCAD","20/3/2019", "19:48", "1.75483");
		Market two = new Market("GBPCAD", "20/3/2019", "19:47", "1.75483");
		Market three = new Market("GBPCAD", "20/3/2019", "19:46", "1.75483");
		Market four = new Market("GBPCAD", "22/2/2019", "3:36", "1.75752");
		Market five = new Market("GBPCAD", "22/2/2019", "3:35", "1.7576");
		Market six = new Market("GBPCAD", "22/2/2019", "3:34", "1.75768");
				
		linkRBT.add(one);
		linkRBT.add(two);
		linkRBT.add(three);
		linkRBT.add(four);
		linkRBT.add(five);
		linkRBT.add(six);
	}
	@Test
	public void setUp5() {
		linkRBT = new RedBlackTree<Market>();
		Market one = new Market("GBPCAD","20/3/2019", "19:48", "1.75483");
		Market two = new Market("GBPCAD", "20/3/2019", "19:47", "1.75483");
		Market three = new Market("GBPCAD", "20/3/2019", "19:46", "1.75483");
		Market four = new Market("GBPCAD", "22/2/2019", "3:36", "1.75752");
		Market five = new Market("GBPCAD", "22/2/2019", "3:35", "1.7576");
		Market six = new Market("GBPCAD", "22/2/2019", "3:34", "1.75768");
				
		linkRBT.add(one);
		linkRBT.add(two);
		linkRBT.add(three);
		linkRBT.add(four);
		linkRBT.add(five);
		linkRBT.add(six);
	}
	
	@Test
	public void setUp6() {
		linkRBT = new RedBlackTree<Market>();
		Market one = new Market("GBPCAD","20/3/2019", "19:48", "1.75483");
		Market two = new Market("GBPCAD", "20/3/2019", "19:47", "1.75483");
		Market three = new Market("GBPCAD", "20/3/2019", "19:46", "1.75483");
		Market four = new Market("GBPCAD", "22/2/2019", "3:36", "1.75752");
		Market five = new Market("GBPCAD", "22/2/2019", "3:35", "1.7576");
		Market six = new Market("GBPCAD", "22/2/2019", "3:34", "1.75768");
				
		linkRBT.add(one);
		linkRBT.add(two);
		linkRBT.add(three);
		linkRBT.add(four);
		linkRBT.add(five);
		linkRBT.add(six);
	}
	
	public void StageOne() {
		
		setUp1();
		assertEquals(6,linkRBT.getElements());
	}
	
	public void StageTwo() {
		
		setUp2();
		assertEquals(5,linkRBT.getElements());
	}
	
	public void Stagethree() {
		setUp3();
		Market latest = new Market("GBPCAD", "22/2/2019", "3:34", "1.75768");
		assertEquals(latest,linkRBT.getUltimoVerticeAgregado());
	}
	
	public void StageFour() {
		setUp4();
		
		assertEquals(3,linkRBT.profundidad());
	}
	
	public void StageFive() {
		setUp5();
		Market elem = new Market("GBPCAD","20/3/2019", "19:48", "1.75483");
		assertEquals(true,linkRBT.contain(elem));
	}

	public void StageSix() {
		setUp6();
		assertEquals(false,linkRBT.isEmpty());
		Market one = new Market("GBPCAD","20/3/2019", "19:48", "1.75483");
		Market two = new Market("GBPCAD", "20/3/2019", "19:47", "1.75483");
		Market three = new Market("GBPCAD", "20/3/2019", "19:46", "1.75483");
		Market four = new Market("GBPCAD", "22/2/2019", "3:36", "1.75752");
		Market five = new Market("GBPCAD", "22/2/2019", "3:35", "1.7576");
		Market six = new Market("GBPCAD", "22/2/2019", "3:34", "1.75768");
		
		linkRBT.delete(one);
		linkRBT.delete(two);
		linkRBT.delete(three);
		linkRBT.delete(four);
		linkRBT.delete(five);
		linkRBT.delete(six);
		
		assertEquals(true,linkRBT.isEmpty());
		
		
	}
}
