
<div class="row">
    <div class="col-md-4">
        <div class="form-group">           
            <input disabled name="voornaam" class="form-control" value='<?php echo $user['voornaam'] ?>'>
        </div>
    </div>
    <div class="col-md-4">
        <div class="form-group d-flex">
            <i class="fas fa-envelope-open-text"></i>
            <input disabled name="email" class="form-control" value='<?php echo $user['email'] ?>'>
        </div>
    </div>
    
    <div class="col-md-4">
        <div class="form-group">
        {{ Form::open([ 'url'=>route('changeUserRole'), 'method'=>'post' ]) }}   
        <input type="hidden" name="id" value="<?php echo $user['id'] ?>" >      
            <select name="rollen[]" id="rolSelect">
                <option name="admin" <?php if($rol == 'admin') echo 'selected' ?> value="admin">Admin</option>
                <option name="docent" <?php if($rol == 'docent') echo 'selected' ?> value="docent">Docent</option>
                <option name="student" <?php if($rol == 'student') echo 'selected' ?> value="student">Student</option>
                <option name="user" <?php if($rol == 'user') echo 'selected' ?> value="user">User</option>
            </select>     
            <input type="submit" class="btn btn-dark" text="SUBMIT">
            {{ Form::close()}}      
         </div>
    </div>
</div>