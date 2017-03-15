function [ dxdt ] = dxdtfun( x )

%x = X(1)
%x = X(1);

if nargin < 2
    Y = t;
else
    Y = Yin;
end;

global r k p

dxdt = (r*(1-x/k)) -(x/(1+x^2)); %p = predators on/off


%X = [dxdt;x];

end

