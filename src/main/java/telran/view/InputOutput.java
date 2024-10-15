package telran.view;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.function.Function;
import java.util.function.Predicate;

public interface InputOutput {
	String readString(String prompt);

	void writeString(String str);

	default void writeLine(Object obj) {
		writeString(obj.toString() + "\n");
	}

	default <T> T readObject(String prompt, String errorPrompt, Function<String, T> mapper) {
		boolean running = false;
		T res = null;
		do {
			running = false;
			try {
				String strRes = readString(prompt);
				res = mapper.apply(strRes);
			} catch (Exception e) {
				writeLine(errorPrompt + ": " + e.getMessage());
				running = true;
			}
		} while (running);
		return res;
	}

	/**
	 * 
	 * @param prompt
	 * @param errorPrompt
	 * @return Integer number
	 */
	default Integer readInt(String prompt, String errorPrompt) {
		return readObject(prompt, errorPrompt, Integer::parseInt);
	}

	default Long readLong(String prompt, String errorPrompt) {
		return readObject(prompt, errorPrompt, Long::parseLong);
	}

	default Double readDouble(String prompt, String errorPrompt) {
		return readObject(prompt, errorPrompt, Double::parseDouble);
	}

	default Double readNumberRange(String prompt, String errorPrompt, double min, double max) {
		return readObject(prompt, errorPrompt, str -> {
			double res = Double.parseDouble(str);
			if (res < min || res > max) {
				throw new IllegalArgumentException("Value must be between " + min + " and " + max);
			}
			return res;
		});
	}

	default String readStringPredicate(String prompt, String errorPrompt, Predicate<String> predicate) {
		return readObject(prompt, errorPrompt, str -> {
			if (!predicate.test(str)) {
				throw new IllegalArgumentException("The entered string does not match the condition");
			}
			return str;
		});
	}

	default String readStringOptions(String prompt, String errorPrompt, HashSet<String> options) {
		return readObject(prompt, errorPrompt, str -> {
			if (!options.contains(str)) {
				throw new IllegalArgumentException("The string entered does not match a valid value");
			}
			return str;
		});
	}

	default LocalDate readIsoDate(String prompt, String errorPrompt) {
		return readObject(prompt, errorPrompt, LocalDate::parse);
	}

	default LocalDate readIsoDateRange(String prompt, String errorPrompt, LocalDate from, LocalDate to) {
		return readObject(prompt, errorPrompt, str -> {
			LocalDate res = LocalDate.parse(str);
			if (res.isBefore(from) || res.isAfter(to)) {
				throw new IllegalArgumentException("The date entered must be between " + from + " and " + to);
			}
			return res;
		});
	}
}
