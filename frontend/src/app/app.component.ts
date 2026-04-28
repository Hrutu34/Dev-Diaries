import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';

/**
 * Root App Component
 */
@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Dev-Diaries';
}
