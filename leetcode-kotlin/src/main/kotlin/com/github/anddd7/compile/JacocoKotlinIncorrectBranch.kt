package com.github.anddd7.compile

class JacocoKotlinIncorrectBranch {

    fun double_safe_call_operator(str: String?): String? {
        return str?.trim()?.capitalize()
    }
    /*
    public final String double_safe_call_operator(@Nullable String str) {
      String var10000;
      if (str != null) {
         boolean var3 = false;
         if (str == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
         }

         var10000 = StringsKt.trim((CharSequence)str).toString();
         if (var10000 != null) {
            var10000 = StringsKt.capitalize(var10000);
            return var10000;
         }
      }

      var10000 = null;
      return var10000;
   }
     */

    fun one_safe_call_operator(str: String?): String? {
        str?: return null
        val result = str.trim() ?: return null
        return result.capitalize()
    }
    /*
    public final String one_safe_call_operator(@Nullable String str) {
      if (str != null) {
         boolean var4 = false;
         if (str == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
         }

         String var10000 = StringsKt.trim((CharSequence)str).toString();
         if (var10000 != null) {
            String result = var10000;
            return StringsKt.capitalize(result);
         }
      }

      return null;
   }
     */
}
