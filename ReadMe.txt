I have designed my test cases to ignore the Recaptcha pop up image quiz knowing that it will probably be disabled on any test environment 
and it doesn't need testing.
so the Function GetErrorMessageUnderField() in ApplicationForm Class gets only the first error message under the field (i know the xpath gets 
the error message under any field and the error message under the Recaptcha as well).