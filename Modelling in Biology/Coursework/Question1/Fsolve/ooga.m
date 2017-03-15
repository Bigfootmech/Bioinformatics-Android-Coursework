
        c = -1; % define parameter first
        x = fsolve(@(x) myfun(x,c),[-5;-5])