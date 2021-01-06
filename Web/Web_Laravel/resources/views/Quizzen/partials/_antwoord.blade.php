<!-- antwoord snipper -->


<!-- bij name="" moet er een array[] worden geplaatst, 
laravel mapt automatisch de values als volgt {
    "name" : "id"
} 
wat dus in ons geval
"vraagId" : "antwoordId" is
-->


<li>
    <input type="radio" id="<?php echo $antwoord['antwoordid']?>" 
    name="choice[<?php echo $vraag['vraagid']?>]" 
    value="<?php echo $antwoord['antwoordid']?>">
    <label for="<?php echo $antwoord['antwoordid']?>"><?php echo $antwoord['antwoord']?></label>
</li>