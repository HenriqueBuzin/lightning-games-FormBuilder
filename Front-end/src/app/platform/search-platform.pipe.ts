// Angular
import { Pipe , PipeTransform } from '@angular/core';

// Model
import { Platform } from './../_models/platform';

@Pipe({
    name: 'searchPlatform',
    pure: false
})
export class SearchPlatformPipe implements PipeTransform {

    transform(platforms: Platform[], typed) {

        typed = typed.toLowerCase();

        return platforms.filter(platform =>

            (platform.name.toLowerCase().includes(typed))

        );

    }

}
