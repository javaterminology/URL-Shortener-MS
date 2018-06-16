import { TestBed, inject } from '@angular/core/testing';

import { UrlShortenerService } from './url-shortener.service';

describe('UrlShortenerService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [UrlShortenerService]
    });
  });

  it('should be created', inject([UrlShortenerService], (service: UrlShortenerService) => {
    expect(service).toBeTruthy();
  }));
});
