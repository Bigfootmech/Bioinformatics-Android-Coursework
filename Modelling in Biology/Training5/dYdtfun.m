function [ dYdt ] = dYdtfun( t,Yin )

if nargin < 2
    Y = t;
else
    Y = Yin;
end;

global n

y= Y(1);
dydt = Y(2);
d2ydt = -n*dydt - y;


dYdt = [dydt; d2ydt];    % pack the results in a column vector



end

