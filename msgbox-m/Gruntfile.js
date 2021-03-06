path = require('path');

module.exports = function(grunt) {

    grunt.initConfig
    ({
        releaseDate:String(new Date().getTime()),
        pkg : grunt.file.readJSON('package.json'),
        wsContext: 'msgbox-ws',
        clientContext: 'msgbox-m',
        wsConfig: require('./server/ws-config.js'),

        compress : {
            main: {
                options: {
                    archive: 'release/<%=clientContext%>.zip'
                },
                files: [
                    {
                        expand: true,
                        cwd: 'dist/',
                        src: ['**/*'],
                        dest: '/'
                    }
                ]
            }
        },
        
        imagemin: {
            img: {
                options: { optimizationLevel: 4 },
                files: [{
                    expand: true,
                    cwd: 'dist/',
                    src: '**/*.{png,jpg}',
                    dest: 'dist'
                }]
            }
        },
        
        uglify: {
        	common: {
                files:{
                    'dist/main_<%=releaseDate%>.js': 'dist/main_<%=releaseDate%>.js',
                    'dist/templates_main_<%=releaseDate%>.js':'dist/templates_main_<%=releaseDate%>.js',
                }
            },
            library:{
                files: {
                    '<%=concat.library.dest%>':'<%=concat.library.dest%>',
                }
            }
        },
        
        clean :{
        	build: {
                src: ["dist/"/*, "test/junit/"*/]
            }
        },

        copy: {
            //–––––––––––––––––––––––––––––––
            // USED FOR COPY TASK
            //–––––––––––––––––––––––––––––––
            common: {
                files: [
                    {
                        expand: true,
                        cwd: 'app/',
                        src: ['**/index.html'],
                        dest: 'dist/'
                    },
                    {
                        expand: true,
                        cwd: 'app/img/',
                        src: ['**/*'],
                        dest: 'dist/img/'
                    },
                    {
                        expand: true,
                        cwd: 'app/fonts/',
                        src: ['**'],
                        dest: 'dist/fonts/'
                    },
                ]
            }
        },

        concat: {
        	css: {
                src : 'app/styles/**/main.css',
                dest : 'dist/styles/main_<%= releaseDate %>.css'
            },
            library: {
                src : [
                    'libs/jquery-1.9.1.js',
                    'libs/handlebars-v1.1.2.js',
                    'libs/*.js'
                ],
                dest : 'dist/libs_<%=releaseDate%>.js'
            },
            main: {
                src: [
                    'app/js/constants.js',
                    'app/js/init.js',
                    'app/js/i18n.js',
                    'app/js/base.js',
                    'app/js/**/*.js'
                    
                ],
                dest : 'dist/main_<%=releaseDate%>.js'
            }
        },

        emberTemplates : {
            compile : {
                options : {
                    templateName : function(sourceFile) {
                        return sourceFile.substring(sourceFile.lastIndexOf('/') + 1);
                    }
                },
                files : {
                    'dist/templates_main_<%=releaseDate%>.js' : [
                        'app/js/**/*.hbs',
                    ]
                }
            },    
        },

        replace : {
            html : {
                options : {
                    variables : {
                        'cssName' : 'main_<%=releaseDate%>',
                        'version' : '<%=releaseDate%>'
                    },
                    prefix : '@@'
                },
                files : {
                    'dist/index.html' : ['dist/index.html']
                }
            },

            wrapperWs : {
                options : {
                    variables : {
                        'webserviceUrl' : "http://<%=wsConfig[env]['host']%>:<%=wsConfig[env]['port']%>/<%=wsContext%>/services/"
                    },
                    prefix : '@@'
                },
                files : {
                    'dist/main_<%=releaseDate%>.js' : [ 'dist/main_<%=releaseDate%>.js' ]
                }
            },

            webWs : {
                options : {
                    variables : {
                        'webserviceUrl' : "/<%=wsContext%>/services/"
                    },
                    prefix : '@@'
                },
                files : {
                    'dist/main_<%=releaseDate%>.js' : [ 'dist/main_<%=releaseDate%>.js' ]
                }
            }
        }
    });

	// Default task.
	grunt.loadNpmTasks('grunt-contrib-copy');
    grunt.loadNpmTasks('grunt-replace');
    grunt.loadNpmTasks('grunt-contrib-concat');
    grunt.loadNpmTasks('grunt-contrib-clean');
    grunt.loadNpmTasks('grunt-contrib-compress');
	grunt.loadNpmTasks('grunt-ember-handlebars');
	grunt.loadNpmTasks('grunt-ember-templates');

    //grunt.loadNpmTasks('grunt-contrib-uglify');
    //grunt.loadNpmTasks('grunt-contrib-imagemin');
    //grunt.loadNpmTasks('grunt-contrib-jshint');
    //grunt.loadNpmTasks('grunt-contrib-jasmine');

    grunt.registerTask(
        'server',
        'Start a custom web server.',
        function() {
            this.async();
            var t_env = grunt.config('env');
            var t_options = {
                wsConfig: grunt.config('wsConfig')[t_env],
                src: 'dist',
                clientContext: grunt.config('clientContext'),
                wsContext: grunt.config('wsContext')
            };
            require('./server/server.js').run(t_options);

            grunt.log.writeln('Server is starting....');

        });

    grunt.registerTask('env', 'replace wrapper ws url', function() {
        var t_args = [].slice.call(arguments);
        var t_env = t_args[0];
        grunt.config('env', t_env);
    })

    grunt.registerTask('build', [
                                 'clean', 
                                 'emberTemplates', 
                                 'concat', 
                                 'copy', 
                                 'replace:html',
                                 'replace:webWs'/*, 
                                 'imagemin'*/
                                 /*'jasmine',*/
                                 ]);
    grunt.registerTask('uglifyFiles', ['uglify:common', 'uglify:library']);


    grunt.registerTask('run-sit', [
        'env:sit',
        'build',
        'server'
    ]);

    grunt.registerTask('run-mock', [
        'env:mock',
        'build',
        'server'
    ]);

    grunt.registerTask('package', [
        'build',
        /*'uglifyFiles',*/
        'compress'
    ]);

    grunt.registerTask('wrapper-sit', [
        'env:sit',
        'clean', 
        'emberTemplates', 
        'concat', 
        'copy', 
        'replace:html',
        'replace:wrapperWs'
    ]);
};