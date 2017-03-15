function [ dxdt ] = dxdtfun( t,x )

%x = X(1)
%x = X(1);

%if nargin < 2
%    Y = t;
%else
%    Y = Yin;
%end;

global r k p

dxdt = (r*x*(1-x/k)) -(p*x^2/(1+x^2)); %p = predators on/off


%X = [dxdt;x];

end

