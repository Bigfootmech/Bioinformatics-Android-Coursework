function [ Ek ] = DynSys( a, t )

E0 = 5;
a = a;

Ek = zeros(1, length(t));
Ek(1) = E0;
for i = t; %1:1:30
    Ek(i+1) = a*Ek(i);
end

Ek(length(t)) = []; %discard the last element to match the lengths

end

