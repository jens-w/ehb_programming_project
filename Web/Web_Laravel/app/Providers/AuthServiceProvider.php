<?php

namespace App\Providers;
use Auth;
use App\Providers\CustomUserProvider;
use Illuminate\Support\ServiceProvider;
class AuthServiceProvider extends ServiceProvider
{
    /**
     * Perform post-registration booting of services.
     *
     * @return void
     */
    public function boot()
    {
        \Illuminate\Support\Facades\Auth::provider('coursequiz', function($app, array $config) {
            return new CustomUserProvider($app['hash'], $config['model']);
        });
    }
    /**
     * Register bindings in the container.
     *
     * @return void
     */
    public function register()
    {
        //
    }
}
