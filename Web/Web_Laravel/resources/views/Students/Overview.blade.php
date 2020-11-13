@extends('main')

@section('content')
<div class="container">
    <div class="row">
        <?php foreach ($studentNames as $name): ?>
        <div class="col-6">
        Email : 
        </div>
        <div class="col-6">
        <label>
            <?php echo $name ?>
        </label>
        </div>
        <?php endforeach; ?>
    </div>
</div>
@endsection