package ch.julien.query.util;

import static org.fest.assertions.api.Assertions.assertThat;

import org.junit.Test;


public class FuncsTest {

	private static interface ITestType {}
	private static class TestType implements ITestType {}
	private static class TestSubtype extends TestType {}

	@Test
	public void testTo() {
		// implicitly assert absence of class cast exceptions when assigning to var!
		TestType var;
		ITestType ivar;

		// to same type
		var = Funcs.to(TestType.class).invoke(new TestType());
		assertThat(var).isNotNull();

		// to interface
		ivar = Funcs.to(ITestType.class).invoke(new TestType());
		assertThat(ivar).isNotNull();

		// to supertype
		var = Funcs.to(TestType.class).invoke(new TestSubtype());
		assertThat(var).isNotNull();

		// to interface of supertype
		ivar = Funcs.to(ITestType.class).invoke(new TestSubtype());
		assertThat(ivar).isNotNull();

		// anonymous subtype to type
		var = Funcs.to(TestType.class).invoke(new TestType() {});
		assertThat(var).isNotNull();

		// anonymous subtype to interface
		ivar = Funcs.to(ITestType.class).invoke(new TestType() {});
		assertThat(ivar).isNotNull();

		// variable of supertype to type
		Object testClassInstance = new TestType();
		var = Funcs.to(TestType.class).invoke(testClassInstance);
		assertThat(var).isNotNull();

		// variable of supertype to interface
		ivar = Funcs.to(ITestType.class).invoke(testClassInstance);
		assertThat(ivar).isNotNull();
	}

	@Test(expected=ClassCastException.class)
	public void testTo_Exception1() {
		// supertype to type
		@SuppressWarnings("unused")	// no exception without assignment
		TestType var = Funcs.to(TestType.class).invoke(new Object());
	}

	@Test(expected=ClassCastException.class)
	public void testTo_Exception2() {
		// supertype to interface
		@SuppressWarnings("unused")	// no exception without assignment
		ITestType ivar = Funcs.to(ITestType.class).invoke(new Object());
	}

	@Test(expected=ClassCastException.class)
	public void testTo_Exception3() {
		// unrelated type to type
		@SuppressWarnings("unused")	// no exception without assignment
		TestType var = Funcs.to(TestType.class).invoke("instance of unrelated type");
	}

	@Test(expected=ClassCastException.class)
	public void testTo_Exception4() {
		// unrelated type to interface
		@SuppressWarnings("unused")	// no exception without assignment
		ITestType ivar = Funcs.to(ITestType.class).invoke("instance of unrelated type");
	}

	@Test
	public void testReplaceAll() {
		assertThat(Funcs.replaceAll("regex", "replacement").invoke("")).isEqualTo("");
		assertThat(Funcs.replaceAll("regex", "replacement").invoke("no match")).isEqualTo("no match");
		assertThat(Funcs.replaceAll("regex", "replacement").invoke("regex and shit")).isEqualTo("replacement and shit");
	}

	@Test(expected=NullPointerException.class)
	public void testReplaceAll_Exception1() {
		Funcs.replaceAll("regex", "replacement").invoke(null);
	}

	@Test
	public void testTrimString() {
		assertThat(Funcs.trimString().invoke("")).isEqualTo("");
		assertThat(Funcs.trimString().invoke(" ")).isEqualTo("");
		assertThat(Funcs.trimString().invoke("\t")).isEqualTo("");
		assertThat(Funcs.trimString().invoke("a a")).isEqualTo("a a");
		assertThat(Funcs.trimString().invoke(" a a")).isEqualTo("a a");
		assertThat(Funcs.trimString().invoke("a a ")).isEqualTo("a a");
	}

	@Test(expected=NullPointerException.class)
	public void testTrimString_Exception1() {
		Funcs.trimString().invoke(null);
	}

	@Test
	public void testToStringRepresentation() {
		final String string = "string";
		assertThat(Funcs.toStringRepresentation().invoke(new Object() {
			@Override
			public String toString() {
				return string;
			}
		})).isEqualTo(string);
	}

	@Test(expected=NullPointerException.class)
	public void testToStringRepresentation_Exception1() {
		Funcs.toStringRepresentation().invoke(null);
	}

	@Test
	public void testParseInteger() {
		assertThat(Funcs.parseInteger().invoke("1")).isEqualTo(1);
	}

	@Test(expected=NumberFormatException.class)
	public void testParseInteger_Exception1() {
		assertThat(Funcs.parseInteger().invoke(null));
	}

	@Test(expected=NumberFormatException.class)
	public void testParseInteger_Exception2() {
		assertThat(Funcs.parseInteger().invoke(""));
	}

	@Test(expected=NumberFormatException.class)
	public void testParseInteger_Exception3() {
		assertThat(Funcs.parseInteger().invoke("not_an_integer"));
	}

	@Test(expected=NumberFormatException.class)
	public void testParseInteger_Exception4() {
		assertThat(Funcs.parseInteger().invoke("1.2"));
	}

	@Test
	public void testParseLong() {
		assertThat(Funcs.parseLong().invoke("1")).isEqualTo(1);
	}

	@Test(expected=NumberFormatException.class)
	public void testParseLong_Exception1() {
		assertThat(Funcs.parseLong().invoke(null));
	}

	@Test(expected=NumberFormatException.class)
	public void testParseLong_Exception2() {
		assertThat(Funcs.parseLong().invoke(""));
	}

	@Test(expected=NumberFormatException.class)
	public void testParseLong_Exception3() {
		assertThat(Funcs.parseLong().invoke("not_a_long"));
	}

	@Test(expected=NumberFormatException.class)
	public void testParseLong_Exception4() {
		assertThat(Funcs.parseLong().invoke("1.2"));
	}

	@Test
	public void testParseDouble() {
		assertThat(Funcs.parseDouble().invoke("1")).isEqualTo(1d);
		assertThat(Funcs.parseDouble().invoke("1.2")).isEqualTo(1.2);
	}

	@Test(expected=NullPointerException.class)
	public void testParseDouble_Exception1() {
		assertThat(Funcs.parseDouble().invoke(null));
	}

	@Test(expected=NumberFormatException.class)
	public void testParseDouble_Exception2() {
		assertThat(Funcs.parseDouble().invoke(""));
	}

	@Test(expected=NumberFormatException.class)
	public void testParseDouble_Exception3() {
		assertThat(Funcs.parseDouble().invoke("no_double"));
	}

	@Test
	public void testSelf() {
		Object o = new Object();
		assertThat(Funcs.self().invoke(o)).isEqualTo(o);
		assertThat(Funcs.self().invoke(null)).isNull();
	}
}
