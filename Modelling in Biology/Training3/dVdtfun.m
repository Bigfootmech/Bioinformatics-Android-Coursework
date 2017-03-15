function [ dVdt ] = dVdtfun( t,Vin )

if nargin < 2
    V = t;
else
    V = Vin;
end;


    global a b g
%a = 1;
%b = 1;
%g = 0.3;


x = V(1);
y = V(2);


dxdt = (a/(1+y^4)-g*x);
dydt = (b/(1+x^4)-g*y);

dVdt = [dxdt; dydt];    % pack the results in a column vector



end

