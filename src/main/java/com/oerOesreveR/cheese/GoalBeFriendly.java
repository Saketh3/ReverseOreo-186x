package com.oerOesreveR.cheese;

import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.Goal;

public class GoalBeFriendly extends Goal {
    @Override
    public boolean shouldExecute() {
        return true;
    }

}
