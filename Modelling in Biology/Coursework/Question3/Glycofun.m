function [ dXdt ] = Glycofun( t,X )

if nargin < 2
    X = t;
end;


    global k1 k2 vin


x = X(1);
y = X(2);


dxdt = (vin - k2*x - k1*x*y^2);
dydt = (k2*x-y+k1*x*y^2);

dXdt = [dxdt; dydt];



end

