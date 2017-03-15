x0 = 5;                     %initial condition
k = 0.25;                   %initial condition
h=0.001;
t=0:h:10;
[t,x]=ode45(@(t,x)-k*x,t,x0); %numerical method
plot(t,x)
%figure;

%%%%%%%%%%%%%%%%%%%%%%%%%%%%% ANALYTICAL PART %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

A = dsolve('Dx=-K*x','x(0)=x0','K=k');    %calculate analytical solution
B = char(A);                %make it readable
C= findstr(B, '{');         %find the { in the string
D= findstr(B, '}');         %find the }
Equ = B(C(1)+1:D(1)-1)      %Isolate the equation, and use it as a function, but as it is it **might not** work with matricies. So...

C=findstr(Equ, 't');        %have to add (a) for the next "for" statement in to the Equ to EVERY 't'

for c=length(C):-1:1;       %count back from the last t (for however many 't's you have)
    Equ = [Equ 'abc'];      %Extend Equ to prevent overflow
    for b=length(Equ)-3:-1:C(c)+1    %Move everything from the last spot three characters to the rear until you hit t. C(c) = where search found 't'
        Equ(b+3) = Equ(b);
    end
    Equ(C(c)+1) = '(';
    Equ(C(c)+2) = 'a';      % Add (a) - ie: overwrite those spots, because we've moved everything out of the way.
    Equ(C(c)+3) = ')';      % This is to make the next "for" loop execute all the way correctly, as we need to do it for each value of t
end
for a=1:length(t)
y(a) = (eval(Equ));         %Evaluate your built string  ----> basically this is y(a) = 4/exp((3*t(a))/5)
end
y = y.';                    %Transpose the matrix, so that it works with t, and so on.

%%%%%%%%%%%%%%%%%%%%%%%%%%%%% ANALYTICAL PART END %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

%y = 4*exp(-k*t);           %Analytical method, using same time steps, copypasted, or worked out by hand
hold;                       %print everything to the same graph
plot(t,y)                   %plot analytical over numerical (can see a very tiny difference)
mean_square_error = mse(x-y)    %calculates and displays mean squared error
P=x-y;                      %error array
Q=P.*P;                     %squared error (in each cell)
R=sum(Q)/length(Q)          %mean squared error (same as with mse function).
%figure();
i=2:length(t);              %redundant variable
timestep(i)=t(i)-t(i-1);    %time step length
plot(t,timestep)            % delta t (step size) vs t or 'Phase plane' for time