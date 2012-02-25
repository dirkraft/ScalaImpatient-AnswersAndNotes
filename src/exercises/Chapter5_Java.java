package exercises;

// Note this can't collide with Chapter5.scala and so is named differently.
public class Chapter5_Java {

	// Corollary Java-equivalent of Chapter5.scala, #8 class Car
	public static class Car {
		public final String manufacturer;
		public final String modelName;
		public final Integer modelYear;
		public String licensePlate = "";

		private final static Integer DEF_MODEL_YEAR = -1;
		private final static String DEF_LICENSE_PLATE = "";

		public Car(String manufacturer, String modelName, Integer modelYear, String licensePlate) {
			this.manufacturer = manufacturer;
			this.modelName = modelName;
			this.modelYear = modelYear;
			this.licensePlate = licensePlate;
		}

		public Car(String manufacturer, String modelName, Integer modelYear) {
			this(manufacturer, modelName, modelYear, DEF_LICENSE_PLATE);
		}

		public Car(String manufacturer, String modelName, String licensePlate) {
			this(manufacturer, modelName, DEF_MODEL_YEAR, licensePlate);
		}

		public Car(String manufacturer, String modelName) {
			this(manufacturer, modelName, DEF_MODEL_YEAR, DEF_LICENSE_PLATE);
		}

		@Override
		public String toString() {
			return "Manufacturer: " + manufacturer + " Model: " + modelName + " Year: " + modelYear + " Plate: "
					+ licensePlate;
		}
	}
}
