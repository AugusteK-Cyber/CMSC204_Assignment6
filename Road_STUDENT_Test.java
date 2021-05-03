import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Road_STUDENT_Test {

	@Test
	public void testGetName() {
		Road r = new Road(new Town("Baltimore"), new Town("Springfield"), 78, "A51-Highway");
		assertEquals("A51-Highway", r.getName());
	}

	@Test
	public void testGetSource() {
		Town t1 = new Town("Baltimore");
		Town t2 = new Town("Springfield");
		Road r = new Road(t1, t2, 78, "G67-Rootway");
		assertEquals(t1, r.getSource());
	}

	@Test
	public void testGetDestination() {
		Town t1 = new Town("Baltimore");
		Town t2 = new Town("Springfield");
		Road r = new Road(t1, t2, 78, "G67-Rootway");
		assertEquals(t2, r.getDestination());
	}

	@Test
	public void testGetWeight() {
		Road r = new Road(new Town("Baltimore"), new Town("Springfield"), 78, "T21-Jull");
		assertEquals(78, r.getWeight());
	}

	@Test
	public void testHashcode() {
		Road r = new Road(new Town("Baltimore"), new Town("Springfield"), 78, "Route396");
		assertEquals(r.hashCode(), r.hashCode());
	}

	@Test
	public void testToString() {
		Road r = new Road(new Town("Baltimore"), new Town("Springfield"), 78, "Route396");
		assertEquals("Baltimore via Route396 to Springfield 78 mi", r.toString());
	}

	@Test
	public void testContains() {
		Town t1 = new Town("Baltimore");
		Town t2 = new Town("Springfield");
		Road r = new Road(t1, t2, 23, "G67-Rootway");
		assertEquals(true, r.contains(t2));
		assertEquals(false, r.contains(new Town("Louisville")));
	}

}
