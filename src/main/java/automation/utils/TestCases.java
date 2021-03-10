package automation.utils;

public enum TestCases {
	T1("Testing the authentication"),
	T2("Testing the purchase of two items");


	private String testName;

	public String getTestName() {
		return testName;
	}

	TestCases(String value) {
		this.testName = value;
	}
}
