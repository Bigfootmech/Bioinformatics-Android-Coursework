function [ dVdt ] = CdVdtfun( V )

a = 1;
b = 1;
g = 0.3;


x = V(1);
y = V(2);


dxdt = (a/(1+y^4)-g*x);
dydt = (b/(1+x^4)-g*y);

dVdt = [dxdt; dydt];    % pack the results in a column vector



end

