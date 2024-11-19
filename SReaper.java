    import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
    import java.util.List;
    import java.io.File;
    /**
     * Write a description of class SReaper here.
     * 
     * @author (your name) 
     * @version (a version number or a date)
     */
    public class SReaper extends Square
    {
        private int deathCount;
        /**
         * Act - do whatever the SReaper wants to do. This method is called whenever
         * the 'Act' or 'Run' button gets pressed in the environment.
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
        
        protected String getName(){
            return "SReaper";
        }
    }
