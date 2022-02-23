package io.pera.bookstore.util;

public class Constants {

    public static final class API {
        public static final String CUSTOMER_MAPPING = "/customers";
        public static final String BOOK_MAPPING = "/books";
        public static final String ORDER_MAPPING = "/orders";
        public static final String STATISTIC_MAPPING = "/statistics";
    }

    public static final class SEQUENCE {
        public static final String CUSTOMER = "customer_seq";
        public static final String BOOK = "book_seq";
        public static final String ORDER = "order_seq";
        public static final String ORDER_ITEM = "order_item_seq";
    }

    public static final class EXCEPTION {
        public static final String DEFAULT_CODE = "SYSTEM";
    }

    public static final class MAPPER {
        public static final String COMPONENT_MODEL = "spring";
    }

}
