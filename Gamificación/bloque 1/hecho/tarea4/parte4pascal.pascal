Program primos;
uses wincrt;
var
  esPrimo: boolean;
  num, divisor: integer;
Begin
   esPrimo:=true;
   divisor:=2;
   WRITELN; 
   write('Introduzca un número===> '); 
   readln(num);
   WRITELN;WRITELN;
   while (divisor<num) and esPrimo  do
   begin
     if num mod divisor = 0 then
          esPrimo:=false;
     divisor:=divisor+1
   end;
 if esPrimo=true then
    writeln(num,' si es número Primo...')
 else
    writeln(num,' no es un número Primo...');
 writeln;
 WRITELN;
end.
