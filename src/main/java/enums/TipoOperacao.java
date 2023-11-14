package enums;

public class TipoOperacao {

	public enum TipoOperacaoEnum {
		WITHDRAW, DEPOSIT;
	}

	public static String enumToString(TipoOperacaoEnum tipo) {
		switch (tipo) {
			case DEPOSIT:
				return "D";
			case WITHDRAW:
				return "R";
			default:
				throw new IllegalArgumentException("Invalid TipoOperacaoEnum value: " + tipo);
		}
	}

	public static TipoOperacaoEnum stringToEnum(String tipo) {

		switch (tipo) {
			case "D":
				return TipoOperacaoEnum.DEPOSIT;
			case "R":
				return TipoOperacaoEnum.WITHDRAW;
			default:
				throw new IllegalArgumentException("Invalid String value: " + tipo);
		}

	}
}
