import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShortenedUrlComponent } from './shortened-url.component';

describe('ShortenedUrlComponent', () => {
  let component: ShortenedUrlComponent;
  let fixture: ComponentFixture<ShortenedUrlComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShortenedUrlComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShortenedUrlComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
