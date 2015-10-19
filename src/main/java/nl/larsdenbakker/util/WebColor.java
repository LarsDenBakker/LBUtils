package nl.larsdenbakker.util;

/**
 *
 * @author Lars den Bakker <larsdenbakker at gmail.com>
 */
public enum WebColor implements Describable {

   ALICE_BLUE("F0F8FF"),
   ANTIQUE_WHITE("FAEBD7"),
   AQUA("00FFFF"),
   AQUAMARINE("7FFFD4"),
   AZURE("F0FFFF"),
   BEIGE("F5F5DC"),
   BISQUE("FFE4C4"),
   BLACK("000000"),
   BLANCHED_ALMOND("FFEBCD"),
   BLUE("0000FF"),
   BLUE_VIOLET("8A2BE2"),
   BROWN("A52A2A"),
   BURLY_WOOD("DEB887"),
   CADET_BLUE("5F9EA0"),
   CHARTREUSE("7FFF00"),
   COCOLATE("D2691E"),
   CORAL("FF7F50"),
   CORNFLOWER_BLUE("6495ED"),
   CORNSILK("FFF8DC"),
   CRIMSON("DC143C"),
   CYAN("00FFFF"),
   DARK_BLUE("00008B"),
   DARK_CYAN("008B8B"),
   DARK_GOLDENROD("B8860B"),
   DARK_GRAY("A9A9A9"),
   DARK_GREEN("006400"),
   DARK_KHAKI("BDB76B"),
   DARK_MAGENTA("8B008B"),
   DARK_OLIVE_GREEN("556B2F"),
   DARK_ORANGE("FF8C00"),
   DARK_ORCHID("9932CC"),
   DARK_RED("8B0000"),
   DARK_SALMON("E9967A"),
   DARK_SEA_GREEN("8FBC8F"),
   DARK_SLATE_BLUE("483D8B"),
   DARK_SLATE_GRAY("2F4F4F"),
   DARK_TURQUOISE("00CED1"),
   DARK_VIOLET("9400D3"),
   DEEP_PINK("FF1493"),
   DEEP_SKY_BLUE("00BFFF"),
   DIM_GRAY("696969"),
   DODGER_BUE("1E90FF"),
   FIRE_BRICK("B22222"),
   FLORAL_WHITE("FFFAF0"),
   FOREST_GREEN("228B22"),
   FUCHSIA("FF00FF"),
   GAINSBORO("DCDCDC"),
   GHOST_WHITE("F8F8FF"),
   GOLD("FFD700"),
   GOLDENROD("DAA520"),
   GRAY("808080"),
   GREEN("008000"),
   GREEN_YELLOW("ADFF2F"),
   HONEYDEW("F0FFF0"),
   HOT_PINK("FF69B4"),
   INDIAN_RED("CD5C5C"),
   INDIGO("4B0082"),
   IVORY("FFFFF0"),
   KHAKI("F0E68C"),
   LAVENDER("E6E6FA"),
   LAVENDER_BLUSH("FFF0F5"),
   LAWN_GREEN("7CFC00"),
   LEMON_CHIFFON("FFFACD"),
   LIGHT_BLUE("ADD8E6"),
   LIGHT_CORAL("F08080"),
   LIGHT_CYAN("E0FFFF"),
   LIGHT_GOLDENROD_YELLOW("FAFAD2"),
   LIGHT_GREEN("90EE90"),
   LIGHT_GREY("D3D3D3"),
   LIGHT_PINK("FFB6C1"),
   LIGHT_SALMON("FFA07A"),
   LIGHT_SEA_GREEN("20B2AA"),
   LIGHT_SKY_BLUE("87CEFA"),
   LIGHT_SLATE_GRAY("778899"),
   LIGHT_STEEL_BLUE("B0C4DE"),
   LIGHT_YELLOW("FFFFE0"),
   LIME("00FF00"),
   LIME_GREEN("32CD32"),
   LINEN("FAF0E6"),
   MAGENTA("FF00FF"),
   MAROON("800000"),
   MEDIUM_AQUAMARINE("66CDAA"),
   MEDIUM_BLUE("0000CD"),
   MEDIUM_ORCHID("BA55D3"),
   MEDIUM_PURPLE("9370DB"),
   MEDIUM_SEA_GREEN("3CB371"),
   MEDIUM_SLATE_BLUE("7B68EE"),
   MEDIUM_SPRING_GREEN("00FA9A"),
   MEDIUM_TURQUOISE("48D1CC"),
   MEDIUM_VIOLET_RED("C71585"),
   MIDNIGHT_BLUE("191970"),
   MINT_CREAM("F5FFFA"),
   MISTY_ROSE("FFE4E1"),
   MOCCASIN("FFE4B5"),
   NAVAJO_WHITE("FFDEAD"),
   NAVY("000080"),
   OLD_LACE("FDF5E6"),
   OLIVE("808000"),
   OLIVE_DRAB("6B8E23"),
   ORANGE("FFA500"),
   ORANGE_RED("FF4500"),
   ORCHID("DA70D6"),
   PALE_GOLDENROD("EEE8AA"),
   PALE_GREEN("98FB98"),
   PALE_TURQUOISE("AFEEEE"),
   PALE_VIOLET_RED("DB7093"),
   PAPAYA_WHIP("FFEFD5"),
   PEACH_PUFF("FFDAB9"),
   PERU("CD853F"),
   PINK("FFC0CB"),
   PLUM("DDA0DD"),
   POWDER_BLUE("B0E0E6"),
   PURPLE("800080"),
   RED("FF0000"),
   ROSY_BROWN("BC8F8F"),
   ROYAL_BLUE("4169E1"),
   SADDLE_BROWN("8B4513"),
   SEA_GREEN("2E8B57"),
   SEA_SHELL("FFF5EE"),
   SIENNA("A0522D"),
   SILVER("C0C0C0"),
   SKY_BLUE("87CEEB"),
   SLATE_BLUE("6A5ACD"),
   SLATE_GRAY("708090"),
   SNOW("FFFAFA"),
   SPRING_GREEN("00FF7F"),
   STEEL_BLUE("4682B4"),
   TAN("D2B48C"),
   TEAL("008080"),
   THISTLE("D8BFD8"),
   TOMATO("FF6347"),
   TURQUOISE("40E0D0"),
   VIOLET("EE82EE"),
   WHEAT("F5DEB3"),
   WHITE_SMOKE("F5F5F5"),
   YELLOW("FFFF00"),
   YELLOW_GREEN("9aCD32");

   String niceName;
   String hex;

   WebColor(String hex) {
      this.niceName = TextUtils.capitalizeAllWords(toString().replaceAll("_", " "));
      this.hex = hex;
   }

   public int getHex() {
      return Integer.parseInt(hex, 16);
   }

   public String getHexString() {
      return hex;
   }

   @Override
   public String getDescription() {
      return niceName;
   }

   public static WebColor getRandomColor() {
      WebColor[] values = WebColor.values();
      int rand = NumberUtils.randInt(1, values.length) - 1;
      for (int i = 0; i < values.length; i++) {
         if (i == rand) {
            return values[i];
         }
      }
      return null;
   }

   public static WebColor getByString(String arg) {
      WebColor color = WebColor.valueOf(arg.replaceAll(" ", "_").toUpperCase());
      return color;
   }

   public static String getWebLink() {
      return "http://www.javascripter.net/faq/colornam.htm";
   }

}