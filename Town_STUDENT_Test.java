import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Town_STUDENT_Test {

	@Test
	public void testGetName() {
		Town t1 = new Town("townA");
		Town t2 = new Town("townB");
		assertEquals("townA", t1.getName());
		assertEquals("townB", t2.getName());
	}

	@Test
	public void testHashcode() {
		Town t1 = new Town("townA");
		Town t2 = new Town("townB");
		assertEquals(110553071, t1.hashCode());
		assertEquals(110553072, t2.hashCode());
	}

	@Test
	public void testToString() {
		Town t1 = new Town("townA");
		Town t2 = new Town("townB");
		assertEquals("townA", t1.toString());
		assertEquals("townB", t2.toString());
	}

	@Test
	public void testEqualsTown() {
		Town t1 = new Town("townA");
		Town t2 = new Town("townB");
		assertFalse(t1.equals(t2));
		assertTrue(t2.equals(t2));
	}

}
