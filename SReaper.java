    import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
    import java.util.List;
    import java.io.File;
    /**
     * Write a description of class SReaper here.
     * 
     * @author Justin Ye 
     * @version 10
     */
    public class SReaper extends Square
    {
        private int deathCount;
        /**
         * Act - do whatever the SReaper wants to do. once death count exceeds limit it will 
         * automatically die
         */
        public void act()
        {
            super.act();
            if(deathCount > 10)
            {
                createGhost();
                getWorld().removeObject(this);
            }
        }
        
        /**
         * This constructor creates the SReaper, since its a special unit it has no stage, 
         * it will still take atk, health, atkCooldown and frames to use methods from superclass Square 
         * and Unit.
         */
        public SReaper()
        {
            super();
            deathCount = 0;
            imageScale = 0.85;
            attackXOffset = 0;
            attackYOffset = 0;
            setAtkSoundEffect(0,80);
            loadAnimationFrames("images/Units/SReaper");
            attackFrame = 1;        
            knockbacks = 0;
            atkCooldown = 2;
            speed = 2;
            atk = 500;
            health = 50;
        }
        
        /**
         * It takes a circle in range and everytime it kills a circle its deathcount goes up. The 
         * Tower takes its attack/25 to balance out the game and not have an instant game over.
         */
        protected void attack(){
            List<Circle> potentialTargets = getObjectsInRange(range, Circle.class);
            List<Tower> towerTarget = getObjectsInRange(2 * range,Tower.class);
            Tower tower = towerTarget.size() > 0 ? towerTarget.get(0) : null;
            if(potentialTargets.size() > 0||towerTarget.size() > 0)
            {
                Circle target = potentialTargets.size() > 0 ? potentialTargets.get(0) : null;
                for(int i = 0; i < potentialTargets.size(); i++)
                {
                    if(potentialTargets.get(i).getNormalX() < target.getNormalX())
                    {
                        target = potentialTargets.get(i);
                    }
                }
                if(tower != null && tower.getCircle() && (target == null || tower.getX() < target.getNormalX()))
                {
                    tower = towerTarget.get(0);
                    target = null;
                }
                if(target != null)
                {
                    target.hurt(atk);
                    atkSoundEffect.play();
                    deathCount++;
                }
                else if(tower != null)
                {
                    tower.hurt(atk/25);
                    atkSoundEffect.play();
                    deathCount++;
                }
            }
        }
        
        /**
         * The png version corrupts the animation method for some reason.
         */
        protected void loadAnimationFrames(String path)
        {
            //Important: Ensure all folders are labelled with "attack", "move", and "stand"
            for(int i = 0; i < new File(path+"/attack").listFiles().length-1; i++)
            {
                attackAnim.add(new GreenfootImage(path + "/attack/" + i + ".gif"));
                attackAnim.get(i).scale((int)(attackAnim.get(i).getWidth()*imageScale),(int)(attackAnim.get(i).getHeight()*imageScale));
            }
            for(int i = 0; i < new File(path+"/move").listFiles().length-1; i++)
            {
                walkAnim.add(new GreenfootImage(path + "/move/" + i + ".gif"));
                walkAnim.get(i).scale((int)(walkAnim.get(i).getWidth()*imageScale),(int)(walkAnim.get(i).getHeight()*imageScale));
            }
            for(int i = 0; i < new File(path+"/stand").listFiles().length-1; i++)
            {
                idleAnim.add(new GreenfootImage(path + "/stand/" + i + ".gif"));
                idleAnim.get(i).scale((int)(idleAnim.get(i).getWidth()*imageScale),(int)(idleAnim.get(i).getHeight()*imageScale));
            }
        }
        
        /**
         * Gets the name of the unit
         */
        protected String getName(){
            return "SReaper";
        }
    }
