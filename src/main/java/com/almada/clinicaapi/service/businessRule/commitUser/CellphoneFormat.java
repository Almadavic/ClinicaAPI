package com.almada.clinicaapi.service.businessRule.commitUser;

import com.almada.clinicaapi.service.customException.InvalidCellphoneNumberFormatException;

public class CellphoneFormat {

    private CellphoneFormat() {

    }

    public static String verification(String celular) {

        if (celular != null) {

            if (celular.startsWith("55") || celular.startsWith("+55")) {
                celular = celular.replaceFirst("55", "")
                        .replace("+", "");
            }

            String cellphoneValidFormat = "^\\([1-9]{2}\\)9?[6-9][0-9]{3}\\-[0-9]{4}$";

            if (!celular.matches(cellphoneValidFormat)) {
                throw new InvalidCellphoneNumberFormatException(celular);
            }

            return celular;
        }

        return null;
    }

}
